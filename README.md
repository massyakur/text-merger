# Text Merger

Text Merger is a simple and efficient tool to combine multiple text files or string inputs into a single output. It is designed for users who need to merge content for documentation, data processing, or any workflow that involves aggregating textual information.

## Features

- Merge multiple text files into one
- Supports customizable delimiters between merged contents
- Command-line interface for easy use
- Lightweight and fast

## Getting Started

### Prerequisites

- [Python 3.7+](https://www.python.org/downloads/) (if implemented in Python)
- Or refer to the installation section below for other languages

### Installation

Clone the repository:

```bash
git clone https://github.com/massyakur/text-merger.git
cd text-merger
```

_If the project uses Python:_

Install dependencies (if any):

```bash
pip install -r requirements.txt
```

### Usage

#### Basic Command

```bash
python text_merger.py file1.txt file2.txt file3.txt -o merged.txt
```

#### Options

- `-o`, `--output` : Specify output file (default: stdout)
- `-d`, `--delimiter` : Specify delimiter between file contents (default: newline)

#### Example

```bash
python text_merger.py input1.txt input2.txt -o result.txt -d "---"
```

This command will merge `input1.txt` and `input2.txt` into `result.txt`, separating each file's content with `---`.

## Contributing

Contributions are welcome! Please open issues or pull requests for suggestions, bug reports, or improvements.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

Created by [massyakur](https://github.com/massyakur)  
For questions, open an issue in this repository.
